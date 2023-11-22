package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.HabilidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.OportunidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.OportunidadeResponseDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioRequestDTO;
import br.com.jfce.apibancotalentos.dto.mapper.DeficienciaMapper;
import br.com.jfce.apibancotalentos.dto.mapper.HabilidadeMapper;
import br.com.jfce.apibancotalentos.dto.mapper.OportunidadeMapper;
import br.com.jfce.apibancotalentos.model.*;
import br.com.jfce.apibancotalentos.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OportunidadeService{
    private final OportunidadeRepository oportunidadeRepository;
    private final OportunidadeMapper oportunidadeMapper;
    private final HabilidadeRepository habilidadeRepository;
    private final HabilidadeMapper habilidadeMapper;
    private final GeneroRepository generoRepository;
    private final RacaRepository racaRepository;
    private final DeficienciaRepository deficienciaRepository;
    private final DeficienciaMapper deficienciaMapper;

    public OportunidadeService(OportunidadeRepository oportunidadeRepository, OportunidadeMapper oportunidadeMapper, HabilidadeRepository habilidadeRepository, HabilidadeMapper habilidadeMapper, GeneroRepository generoRepository, RacaRepository racaRepository, DeficienciaRepository deficienciaRepository, DeficienciaMapper deficienciaMapper) {
        this.oportunidadeRepository = oportunidadeRepository;
        this.oportunidadeMapper = oportunidadeMapper;
        this.habilidadeRepository = habilidadeRepository;
        this.habilidadeMapper = habilidadeMapper;
        this.generoRepository = generoRepository;
        this.racaRepository = racaRepository;
        this.deficienciaRepository = deficienciaRepository;
        this.deficienciaMapper = deficienciaMapper;
    }

    public List<OportunidadeResponseDTO> findAll(){
        return oportunidadeRepository.findAll()
                .stream()
                .map(oportunidadeMapper::toOportunidadeResponseDTO)
                .toList();
    }

    public List<OportunidadeResponseDTO> findAll(Pageable page){
        Page<Oportunidade> oportunidadePage = oportunidadeRepository.findAll(page);
        List<Oportunidade> oportunidadeList = oportunidadePage.getContent();
        return oportunidadeList.stream()
                .map(oportunidadeMapper::toOportunidadeResponseDTO)
                .toList();
    }

    public OportunidadeResponseDTO getById(String id){
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade.get());
    }

    public OportunidadeResponseDTO create(OportunidadeRequestDTO oportunidadeRequest){
        if(this.verificarDatas(oportunidadeRequest.getDataInicial(), oportunidadeRequest.getDataFinal())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
        }

        Oportunidade created = oportunidadeMapper.toOportunidade(oportunidadeRequest);
        this.obterAtributosExistentes(created);
        created.setHabilidades(this.manipularHabilidades(oportunidadeRequest.getHabilidades()));
        created = oportunidadeRepository.save(created);
        return oportunidadeMapper.toOportunidadeResponseDTO(created);
    }

    public OportunidadeResponseDTO update(String id, OportunidadeRequestDTO oportunidadeRequest){
        if(this.verificarDatas(oportunidadeRequest.getDataInicial(), oportunidadeRequest.getDataFinal())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
        }

        Optional<Oportunidade> oportunidadeOptional = oportunidadeRepository.findById(id);
        if(oportunidadeOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Oportunidade oportunidade = oportunidadeOptional.get();
        this.obterAtributosExistentes(oportunidade);
        oportunidade.update(oportunidadeMapper.toOportunidade(oportunidadeRequest));
        oportunidade.setHabilidades(this.manipularHabilidades(oportunidadeRequest.getHabilidades()));
        oportunidade = oportunidadeRepository.save(oportunidade);
        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade);
    }

    public void delete(String id){
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        oportunidadeRepository.delete(oportunidade.get());
    }

    private Set<Habilidade> manipularHabilidades(Set<HabilidadeRequestDTO> habilidadeRequestDTOS){
        Set<Habilidade> habilidadesExistentes = new HashSet<>();
        for(HabilidadeRequestDTO habilidade : habilidadeRequestDTOS) {
            Optional<Habilidade> habilidadeOptional = habilidadeRepository.findByNome(habilidade.getNome());
            if (habilidadeOptional.isPresent()) {
                habilidadesExistentes.add(habilidadeOptional.get());
            }else{
                Habilidade habilidadeCriada = habilidadeMapper.toHabilidade(habilidade);
                habilidadeCriada = habilidadeRepository.save(habilidadeCriada);
                habilidadesExistentes.add(habilidadeCriada);
            }
        }
        return habilidadesExistentes;
    }

    private boolean verificarDatas(LocalDate dataInicial, LocalDate dataFinal){
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.isAfter(dataInicial) || dataAtual.isAfter(dataFinal) || dataInicial.isAfter(dataFinal);
    }

    private void obterAtributosExistentes(Oportunidade oportunidade){
        if(oportunidade.getRaca() != null){
            Optional<Raca> racaOptional = racaRepository.findByDescricao(oportunidade.getRaca().getDescricao());
            racaOptional.ifPresent(oportunidade::setRaca);
        }else if(oportunidade.getGenero() != null){
            Optional<Genero> generoOptional = generoRepository.findByDescricao(oportunidade.getGenero().getDescricao());
            generoOptional.ifPresent(oportunidade::setGenero);
        }else if(oportunidade.getDeficiencia() != null){
            Optional<Deficiencia> deficienciaOptional = deficienciaRepository.findByDescricao(oportunidade.getDeficiencia().getDescricao());
            deficienciaOptional.ifPresent(oportunidade::setDeficiencia);
        }
    }
}
