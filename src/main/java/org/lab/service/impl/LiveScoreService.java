package org.lab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lab.dto.BatsmanDTO;
import org.springframework.stereotype.Component;

@Component
public class LiveScoreService {
    
    private List<BatsmanDTO> scoresList = initialScores();
    
    public List<BatsmanDTO> getScore() {

        Random rand = new Random();

        int randomNum = rand.nextInt((2 - 1) + 1);
        BatsmanDTO BatsmanDTO = scoresList.get(randomNum);

        BatsmanDTO.setBalls(BatsmanDTO.getBalls() + 1);
        BatsmanDTO.setRuns(BatsmanDTO.getRuns() + 1);

        return scoresList;
    }

    private List<BatsmanDTO> initialScores() {

        BatsmanDTO sachin = new BatsmanDTO();

        sachin.setName("Sachin Tendulkar");
        sachin.setRuns(24);
        sachin.setBalls(26);

        BatsmanDTO ganguly = new BatsmanDTO();
        ganguly.setName("Sourav Ganguly");
        ganguly.setRuns(28);
        ganguly.setBalls(30);

        List<BatsmanDTO> scoresList = new ArrayList<BatsmanDTO>();

        scoresList.add(sachin);
        scoresList.add(ganguly);

        return scoresList;
    }

}