package comfortable_andy.damageindicator.runnable;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveRunnable extends BukkitRunnable {

    private final ArmorStand armorStand;

    public RemoveRunnable(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    @Override
    public void run() {
        armorStand.remove();
    }
}
