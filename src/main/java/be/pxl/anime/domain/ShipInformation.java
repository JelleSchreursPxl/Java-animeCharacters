package be.pxl.anime.domain;

import javax.persistence.*;

@Entity
@Table(name="ship_info")
public class ShipInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shipName;
    private String shipSpecialForce;
    private String currentSeaLocation;

    public ShipInformation(){

    }

    // setters
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    public void setShipSpecialForce(String shipSpecialForce) {
        this.shipSpecialForce = shipSpecialForce;
    }
    public void setCurrentSeaLocation(String currentSeaLocation) {
        this.currentSeaLocation = currentSeaLocation;
    }

    // getters
    public Long getId() { return id; }
    public String getShipName() { return shipName; }
    public String getShipSpecialForce() { return shipSpecialForce; }
    public String getCurrentSeaLocation() { return currentSeaLocation; }
}
