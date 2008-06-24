package com.google.code.jsudokusolver;

import java.util.Set;

public interface CandidateChangeEvent {
    Set<Integer> getPreChangeCandidates();
    Set<Integer> getPostChangeCandidates();
    Cell getCell();
}
