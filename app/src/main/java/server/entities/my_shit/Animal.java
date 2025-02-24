package server.entities.my_shit;

import server.entities.Characteristic;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "Animals")
public class Animal {
//    @Id
//    @Column(name = "card")
//    private Long cardId;
//
//    @OneToOne
//    @MapsId // PK = cardId = Card.id
//    @JoinColumn(name = "card_id", nullable = false)
//    private Card card;


    private Characteristic[] characteristics;
    private boolean isCarnivorous;
    private boolean isParasite;
    private boolean isFat;


    private boolean hasSharpVision;
    private boolean hasCamouflage;

    public boolean hasSharpVision() {
        return false;
    }

    public boolean kill(Animal animal) {
        if (animal.hasCamouflage) {
            if (this.hasSharpVision)
                return true;
            return false;
        }

        return true;
    }

}
