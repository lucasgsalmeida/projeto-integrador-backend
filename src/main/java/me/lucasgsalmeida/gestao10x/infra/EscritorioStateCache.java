package me.lucasgsalmeida.gestao10x.infra;

import me.lucasgsalmeida.gestao10x.model.domain.escritorio.Escritorio;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EscritorioStateCache {

    private final Map<Long, Escritorio> escritorioMap = new ConcurrentHashMap<>();

    public void saveEscritorioState(Long id, Escritorio escritorio) {
        escritorioMap.put(id, escritorio);
    }

    public Escritorio getEscritorioState(Long id) {
        return escritorioMap.get(id);
    }

    public void removeEscritorioState(Long id) {
        escritorioMap.remove(id);
    }
}
