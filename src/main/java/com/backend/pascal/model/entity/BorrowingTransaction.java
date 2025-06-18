package com.backend.pascal.model.entity;

import com.backend.pascal.model.enums.BorrowingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a borrowing transaction in the library.
 */
@Entity
@Table(name = "borrowing_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private String borrowerName;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowingStatus status;
}