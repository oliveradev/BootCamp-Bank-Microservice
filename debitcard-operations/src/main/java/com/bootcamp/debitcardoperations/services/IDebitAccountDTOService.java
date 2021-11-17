package com.bootcamp.debitcardoperations.services;

import com.bootcamp.debitcardoperations.documents.dto.DebitAccountDTO;
import reactor.core.publisher.Mono;

public interface IDebitAccountDTOService {
    /**
     * Find by account number mono.
     *
     * @param typeofdebit   the typeofdebit
     * @param accountNumber the account number
     * @return the mono
     */
    public Mono<DebitAccountDTO> findByAccountNumber(String typeofdebit, String accountNumber);

    /**
     * Update debit mono.
     *
     * @param typeofdebit the typeofdebit
     * @param account     the account
     * @return the mono
     */
    public Mono<DebitAccountDTO> updateDebit(String typeofdebit, DebitAccountDTO account);
}