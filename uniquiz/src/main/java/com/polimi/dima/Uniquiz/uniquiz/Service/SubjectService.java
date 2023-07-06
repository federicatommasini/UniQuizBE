package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository repository;

    private QuizService quizService;

    public List<Subject> getSubjects(){
        List<SubjectEntity> subjects = repository.findAll();
        return subjects.stream().map(s-> SubjectMapper.INSTANCE.fromEntity(s)).collect(Collectors.toList());
    }

    public Subject getSubjectById(String id){
        Optional<SubjectEntity> subject = repository.findById(id);
        return subject.map(SubjectMapper.INSTANCE::fromEntity).orElse(null);
    }

    public Subject getSubjectByName(String name){
        Optional<SubjectEntity> subject = repository.findByName(name);
        return subject.map(SubjectMapper.INSTANCE::fromEntity).orElse(null);
    }

    public List<String> getDocumentsLinks(String subjectId){
        Subject subject = getSubjectById(subjectId);
        String base_url = subject.getBase_url();
        List<String> urls = new ArrayList<>();
        for(String s : subject.getPdf_links()){
            urls.add(base_url + s);
        }
        return urls;
    }
    public void updateRanking(String subjectId, String userId){
        Subject subj = getSubjectById(subjectId);
        Map<String, Integer> ranking;
        if(null!= subj.getRanking())
            ranking = subj.getRanking();
        else ranking = new HashMap<>();
        List<String> quizIds = subj.getQuizIds();
        List<Quiz> quizzes = quizIds.stream().map(id -> quizService.getQuizById(id)).collect(Collectors.toList());
        int score = 0;
        int totalPoints = 0;
        int quizDone = 0;
        int totalQuiz = quizzes.size();
        for(Quiz q : quizzes) {
            totalPoints += q.getQuestions().size();
            if (null != q.getScore() && q.getScore().containsKey(userId)) {
                score += q.getScore().get(userId);
                quizDone += 1;
            }
        }
        int assignedScore = (score/totalPoints) + (quizDone/totalQuiz);
        ranking.put(userId,assignedScore);
        subj.setRanking(ranking);
        save(SubjectMapper.INSTANCE.toEntity(subj));
    }

    public void save(SubjectEntity subject){
        repository.save(subject);
    }
}
