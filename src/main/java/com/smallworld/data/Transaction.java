package com.smallworld.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.TransactionDataFetcher;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

public class Transaction {

    @JsonProperty("mtn")
    private long mtn;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("senderFullName")
    private String senderFullName;
    @JsonProperty("senderAge")
    private int senderAge;
    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;
    @JsonProperty("beneficiaryAge")
    private int beneficiaryAge;
    @JsonProperty("issueId")
    private Integer issueId;

    @JsonProperty("issueSolved")
    private boolean issueSolved;
    @JsonProperty("issueMessage")
    private String issueMessage;


    public long getMtn() {
        return mtn;
    }

    public void setMtn(long mtn) {
        this.mtn = mtn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public int getSenderAge() {
        return senderAge;
    }

    public void setSenderAge(int senderAge) {
        this.senderAge = senderAge;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public int getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public void setBeneficiaryAge(int beneficiaryAge) {
        this.beneficiaryAge = beneficiaryAge;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public void setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }




    // Add getters and setters for the fields

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("transactions.json");

            // Read the JSON array into a List of Transaction objects
            List<Transaction> transactions = objectMapper.readValue(new File("C:\\Users\\bakht\\OneDrive\\Desktop\\coding_test_v3\\coding_test\\src\\main\\java\\com\\smallworld\\data\\transactions.json"), new com.fasterxml.jackson.core.type.TypeReference<List<Transaction>>() {
            });
            TransactionDataFetcher myData = new TransactionDataFetcher(transactions);


            System.out.println("Please type the menu number you want select \n" +
                    "1. Get Total Transaction Amount \n" +
                    "2. Get Total Transaction amount send by someone \n" +
                    "3. Get Max Transaction amount \n" +
                    "4. Count Unique Clients \n" +
                    "5. Has open compliance issue \n" +
                    "6. Get Transaction By Beneficiary Name \n" +
                    "7. Get Unsolved Issue IDS \n" +
                    "8. Get All Solved Issue Message \n" +
                    "9. Get Top 3 Transaction Amount \n" +
                    "10. Get Top Sender \n" +
                    "Type Here :");
            Scanner scanner = new Scanner(System.in);


            int a = scanner.nextInt();
            switch (a) {
                case 1 -> System.out.println(myData.getTotalTransactionAmount());
                case 2 -> {
                    scanner.nextLine();
                    String senderName = scanner.nextLine();
                    System.out.println(myData.getTotalTransactionAmountSentBy(senderName));
                }
                case 3 -> System.out.println(myData.getMaxTransactionAmount());
                case 4 -> System.out.println(myData.countUniqueClients());
                case 5 -> {
                    scanner.nextLine();
                    String clientFullName = scanner.nextLine();
                    System.out.println(myData.hasOpenComplianceIssues(clientFullName));
                }
                case 6 -> System.out.println(myData.getTransactionsByBeneficiaryName());
                case 7 -> System.out.println(myData.getUnsolvedIssueIds());
                case 8 -> System.out.println(myData.getAllSolvedIssueMessages());
                case 9 -> System.out.println(myData.getTop3TransactionsByAmount());
                case 10 -> System.out.println(myData.getTopSender());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
