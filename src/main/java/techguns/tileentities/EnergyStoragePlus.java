package techguns.tileentities;

import net.minecraftforge.energy.EnergyStorage;

public class EnergyStoragePlus extends EnergyStorage {

    public EnergyStoragePlus(int capacity) {
        super(capacity);
    }

    public void setEnergyStored(int value) {
        this.energy = value;
    }

}
