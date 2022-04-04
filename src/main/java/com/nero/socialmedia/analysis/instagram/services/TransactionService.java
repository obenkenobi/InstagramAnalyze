package com.nero.socialmedia.analysis.instagram.services;

import java.util.function.Supplier;

public interface TransactionService {
    <T> T runTransaction(Supplier<T> executor);
}
