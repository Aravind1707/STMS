@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String stockSymbol;
    private int quantity;
    private BigDecimal price;
    private String transactionType;

    @CreationTimestamp
    private LocalDateTime timestamp;

    // Getters and Setters
}
