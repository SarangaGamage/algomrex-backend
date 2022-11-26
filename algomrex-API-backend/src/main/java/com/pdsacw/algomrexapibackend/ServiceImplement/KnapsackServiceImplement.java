package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.AnswerResult;
import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import com.pdsacw.algomrexapibackend.Dto.KnapsackTable;
import com.pdsacw.algomrexapibackend.Entity.KnapsackEntity;
import com.pdsacw.algomrexapibackend.Repository.KnapsackRepository;
import com.pdsacw.algomrexapibackend.Repository.WinnerRepository;
import com.pdsacw.algomrexapibackend.Service.KnapsackService;
import com.pdsacw.algomrexapibackend.Utill.Common;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class KnapsackServiceImplement implements KnapsackService {
    int[] itemWeightsArray = new int[10];
    int[] itemProfitsArray = new int[10];

    @Autowired
    WinnerRepository winnerRepository;
    @Autowired
    KnapsackRepository knapsackRepository;

    public int calculateMaximumProfit(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length){
            return 0;
        }
        // define length of profit and assign it to profitArrayLength
        int profitArrayLength = profits.length;

        // creating the grid
        int[][] dynamicProgrammingArrayGrid = new int[profitArrayLength][capacity + 1];

        // first column should be 0
        for (int i = 0; i < profitArrayLength; i++){
            dynamicProgrammingArrayGrid[i][0] = 0;
        }
        // if we have only one weight, we will take it if it is not more than the capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                dynamicProgrammingArrayGrid[0][c] = profits[0];
        }
        // process all sub-arrays for all the capacities
        for (int i = 1; i < profitArrayLength; i++) {
            for (int c = 1; c <= capacity; c++) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c)
                profit1 = profits[i] + dynamicProgrammingArrayGrid[i - 1][c - weights[i]];
                // exclude the item
                profit2 = dynamicProgrammingArrayGrid[i - 1][c];
                // take maximum
                dynamicProgrammingArrayGrid[i][c] = Math.max(profit1, profit2);
            }
        }
        // return the value of bottom right corner
        return dynamicProgrammingArrayGrid[profitArrayLength - 1][capacity];
    }

    @Override
    public ResponseEntity<Object> getTableData() {
        ArrayList<KnapsackTable> knapsackDataTable = new ArrayList<>();
        String[] items = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        Random random = new Random();
        for (int i = 0; i < items.length; i++) {
            itemWeightsArray[i] = random.nextInt(5 - 1) + 1;
            itemProfitsArray[i] = random.nextInt(10 - 5) + 5;
            knapsackDataTable.add(new KnapsackTable(items[i], "Weight", itemWeightsArray[i]));
            knapsackDataTable.add(new KnapsackTable(items[i], "Profit", itemProfitsArray[i]));
        }
//        int maxProfit = calculateMaximumProfit(itemProfitsArray, itemWeightsArray, 10);
//        System.out.println(maxProfit);
        return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, knapsackDataTable, Constant.SUCCESS);
    }


    @Override
    public ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer , int userId) {
        int maxProfit = calculateMaximumProfit(itemProfitsArray, itemWeightsArray, 10);
        System.out.println(maxProfit);
        AnswerResult answerResult = new AnswerResult();
        if (commonUserAnswer.getKnapsackUserAnswer().getTotalProfit() == maxProfit) {
            String gameID = Common.generateGameId("Knap");
            answerResult.setStatus(1);
            answerResult.setResult("Great Correct choices!!");
            winnerRepository.save(Common.saveWinnerUserDetails(userId, gameID, 0));
            KnapsackEntity knapsackEntity = new KnapsackEntity();
            knapsackEntity.setUserId(userId);
            knapsackEntity.setGameId(gameID);
            knapsackEntity.setMaximumProfit(maxProfit);
            knapsackRepository.save(knapsackEntity);
        } else {
            answerResult.setStatus(0);
            answerResult.setResult("Sorry Wrong choices!!");
        }
        return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, answerResult, Constant.SUCCESS);
    }
}
