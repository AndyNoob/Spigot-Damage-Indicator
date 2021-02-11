package comfortable_andy.damageindicator.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandListener implements Listener {

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        if (event.getRightClicked().getScoreboardTags().contains("di")) {
            event.setCancelled(true);
        }
    }

}
