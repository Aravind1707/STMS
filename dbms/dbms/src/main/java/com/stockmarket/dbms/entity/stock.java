@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private String symbol;
    private String companyName;
    private BigDecimal marketPrice;
    private BigDecimal change;
    private BigDecimal percentChange;

    @UpdateTimestamp
    private Timestamp lastUpdated;

    // Getters and Setters
}
