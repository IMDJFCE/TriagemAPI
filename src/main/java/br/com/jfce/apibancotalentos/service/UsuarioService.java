package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.HabilidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioRequestDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.HabilidadeMapper;
import br.com.jfce.apibancotalentos.dto.mapper.UsuarioMapper;
import br.com.jfce.apibancotalentos.model.Genero;
import br.com.jfce.apibancotalentos.model.Habilidade;
import br.com.jfce.apibancotalentos.model.Raca;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.GeneroRepository;
import br.com.jfce.apibancotalentos.repository.HabilidadeRepository;
import br.com.jfce.apibancotalentos.repository.RacaRepository;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    private final RacaRepository racaRepository;

    private final GeneroRepository generoRepository;

    private final HabilidadeRepository habilidadeRepository;

    private final HabilidadeMapper habilidadeMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, RacaRepository racaRepository, GeneroRepository generoRepository, HabilidadeRepository habilidadeRepository, HabilidadeMapper habilidadeMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.racaRepository = racaRepository;
        this.generoRepository = generoRepository;
        this.habilidadeRepository = habilidadeRepository;
        this.habilidadeMapper = habilidadeMapper;
    }

    public List<UsuarioResponseDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    public List<UsuarioResponseDTO> findAll(Pageable page){
        Page<Usuario> usuarioPage = usuarioRepository.findAll(page);
        List<Usuario> usuarioList = usuarioPage.getContent();
        return usuarioList.stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO getById(String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return usuarioMapper.toUsuarioResponseDTO(usuario.get());
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "E-mail já cadastrado!");
        }

        this.obterAtributosExistentes(usuario);
        Usuario created = usuarioMapper.toUsuario(usuario);
        created.setHabilidades(this.manipularHabilidades(usuario.getHabilidades()));
        created = usuarioRepository.save(created);
        return usuarioMapper.toUsuarioResponseDTO(created);
    }

    public UsuarioResponseDTO update(String id, UsuarioRequestDTO usuarioRequest){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        this.obterAtributosExistentes(usuarioRequest);
        Usuario usuario = usuarioOptional.get();
        usuario.update(usuarioMapper.toUsuario(usuarioRequest));
        usuario.setHabilidades(this.manipularHabilidades(usuarioRequest.getHabilidades()));
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    public void delete(String id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Usuario usuario = usuarioOptional.get();
        usuarioRepository.delete(usuario);
    }

    private void obterAtributosExistentes(UsuarioRequestDTO usuario){
        Optional<Raca> racaOptional = racaRepository.findByDescricao(usuario.getRaca().getDescricao());
        racaOptional.ifPresent(usuario::setRaca);

        Optional<Genero> generoOptional = generoRepository.findByDescricao(usuario.getGenero().getDescricao());
        generoOptional.ifPresent(usuario::setGenero);
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
}