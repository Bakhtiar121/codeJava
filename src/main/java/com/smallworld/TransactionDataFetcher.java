package com.smallworld;

import com.smallworld.data.Transaction;

import java.util.*;

public class TransactionDataFetcher {

    List<Transaction> listTransaction;
     public TransactionDataFetcher(List<Transaction> listTransaction){
         this.listTransaction = listTransaction;
     }


    /**
     * Returns the sum of the amounts of all transactions
     */

    public double getTotalTransactionAmount() {
        double totalAmount = 0.0;
        for (Transaction transaction : this.listTransaction) {
            totalAmount += transaction.getAmount();
        }
        return totalAmount;
    }


    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double totalAmount = 0.0;
        for(Transaction transaction : this.listTransaction){
            if (senderFullName.equals(transaction.getSenderFullName())) {
                totalAmount += transaction.getAmount();
            }
        }
       return totalAmount;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        double maxTransaction = 0.0;
        for(Transaction transaction : this.listTransaction){
            if(maxTransaction < transaction.getAmount()){
                maxTransaction = transaction.getAmount();
            }
        }
        return maxTransaction;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        List<Transaction> uniqueClients = new ArrayList<>();

        for(Transaction transaction : this.listTransaction){
            boolean doesExist = false;
            for(Transaction uniqueClient : uniqueClients){
                   if(transaction.getMtn() == uniqueClient.getMtn()){
                       doesExist = true;
                   }
            }
            if (!doesExist) {
                uniqueClients.add(transaction);
            }
        }
        return uniqueClients.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        boolean doesExist = false;
        for (Transaction transaction : this.listTransaction) {
            if (clientFullName.equals(transaction.getSenderFullName()) ||
                    clientFullName.equals(transaction.getBeneficiaryFullName())) {
                if (!transaction.isIssueSolved()) {
                    doesExist = true;
                    break;
                }
            }
        }

        return doesExist;
    }


    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        Map<String, Transaction> transactionHashMap = new HashMap<>();
        for (Transaction transaction : this.listTransaction) {
            String beneficiaryName = transaction.getBeneficiaryFullName();
            transactionHashMap.put(beneficiaryName, transaction);

        }
   return transactionHashMap;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        Set<Integer> hashSet = new HashSet<>();
        for (Transaction transaction : this.listTransaction) {
            if (!transaction.isIssueSolved()){
                hashSet.add((int) transaction.getMtn());
            }
        }
        return hashSet;

    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> solvedMessages = new ArrayList<>();
        for (Transaction transaction : this.listTransaction){
            if (transaction.isIssueSolved()){
                solvedMessages.add(transaction.getIssueMessage());
            }
        }
        return solvedMessages;
    }


    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<String> getTop3TransactionsByAmount() {
        List<Transaction> top3Transactions = new ArrayList<>(this.listTransaction);

        top3Transactions.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());

        if (top3Transactions.size() > 3) {
            top3Transactions = top3Transactions.subList(0, 3);
        }

        List<String> top3Clients = new ArrayList<>();
        for (Transaction transaction : top3Transactions) {
            top3Clients.add(transaction.getSenderFullName());
        }

        return top3Clients;
    }


    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        Map<String, Double> senderTotalAmounts = new HashMap<>();
        for (Transaction transaction : this.listTransaction) {
            String senderFullName = transaction.getSenderFullName();
            double amount = transaction.getAmount();

            senderTotalAmounts.put(senderFullName, senderTotalAmounts.getOrDefault(senderFullName, 0.0) + amount);
        }

        Optional<Map.Entry<String, Double>> topSenderEntry = senderTotalAmounts.entrySet().stream()
                .max(Map.Entry.comparingByValue());


        return topSenderEntry.map(Map.Entry::getKey);
    }


}
