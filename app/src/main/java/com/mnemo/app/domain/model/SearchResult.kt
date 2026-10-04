package com.mnemo.app.domain.model

data class ComparisonRow(
    val model: String,
    val price: String,
    val gpu: String,
    val ram: String,
    val rationale: String
)

data class SearchResult(
    val query: String,
    val answer: String,
    val confidenceText: String,
    val candidateMemories: List<Memory>,
    val sources: List<Memory>,
    val comparisonRows: List<ComparisonRow> = emptyList(),
    val isComparison: Boolean = false
)
