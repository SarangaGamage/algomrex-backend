package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.AnswerResult;
import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import com.pdsacw.algomrexapibackend.Dto.KnapsackTable;
import com.pdsacw.algomrexapibackend.Service.KnapsackService;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class KnapsackServiceImplement implements KnapsackService {
    int[] itemWeightsArray = new int[10];
    int[] itemProfitsArray = new int[10];

    public int calculateMaximumProfit(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;
        int profitArrayLength = profits.length;
        int[][] dynamicProgrammingArray = new int[profitArrayLength][capacity + 1];

        for (int i = 0; i < profitArrayLength; i++)
            dynamicProgrammingArray[i][0] = 0;

        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                dynamicProgrammingArray[0][c] = profits[0];
        }
        for (int i = 1; i < profitArrayLength; i++) {
            for (int c = 1; c <= capacity; c++) {
                int profit1 = 0, profit2 = 0;
                if (weights[i] <= c)
                    profit1 = profits[i] + dynamicProgrammingArray[i - 1][c - weights[i]];
                profit2 = dynamicProgrammingArray[i - 1][c];
                dynamicProgrammingArray[i][c] = Math.max(profit1, profit2);
            }
        }
        return dynamicProgrammingArray[profitArrayLength - 1][capacity];
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
        int maxProfit = calculateMaximumProfit(itemProfitsArray, itemWeightsArray, 10);
        System.out.println(maxProfit);
        return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, knapsackDataTable, Constant.SUCCESS);
    }


    @Override
    public ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer) {
        int maxProfit = calculateMaximumProfit(itemProfitsArray, itemWeightsArray, 10);
        System.out.println(maxProfit);
        AnswerResult answerResult = new AnswerResult();
        if (commonUserAnswer.getKnapsackUserAnswer().getTotalProfit() == maxProfit) {
            answerResult.setStatus(1);
            answerResult.setResult("Correct choices");
        } else {
            answerResult.setStatus(0);
            answerResult.setResult("Wrong choices");
        }
        return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, answerResult, Constant.SUCCESS);
    }
}
