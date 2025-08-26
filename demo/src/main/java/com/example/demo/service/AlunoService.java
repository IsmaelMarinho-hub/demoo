package com.example.demo.service;

import com.example.demo.dito.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repositoty.AlunoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepositoty alunoRepositoty;

    private List<Aluno> findAllAlunos(){
        return alunoRepositoty.findAll();
    }

    private Optional<Aluno> findAlunoById(Long id){
        return alunoRepositoty.findById(id);
    }

    private Aluno saveAluno(Aluno aluno){
        return alunoRepositoty.save(aluno);
    }

    private void deleteAluno(Long id){
        alunoRepositoty.deleteById(id);
    }

    private Aluno updateAluno (Long id, Aluno updateAluno){
        return alunoRepositoty.findById(id)
                .map(aluno -> {
                    aluno.setNome(updateAluno.getNome());
                    aluno.setEmail(updateAluno.getEmail());
                    aluno.setDataNasc(updateAluno.getDataNasc());
                    aluno.setSenha(updateAluno.getSenha());
                    return alunoRepositoty.save(aluno);
                }).orElseThrow(() -> new RuntimeException("Alunno não existe!"));
    }
}
