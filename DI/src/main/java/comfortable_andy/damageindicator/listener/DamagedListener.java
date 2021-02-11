package comfortable_andy.damageindicator.listener;

import comfortable_andy.damageindicator.DamageIndicator;
import comfortable_andy.damageindicator.Util;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class DamagedListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamaged(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (event.getFinalDamage() == 0) {
            return;
        }

        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        DamageIndicator damageIndicator = new DamageIndicator(Math.round(event.getFinalDamage()) * -10, 60, player);
        damageIndicator.displayDamage(Util.randomLocation(event.getEntity().getLocation().add(0, 1.5, 0), 4));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamageProjectile(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile)) {
            return;
        }

        Projectile projectile = (Projectile) event.getDamager();

        if (!(projectile.getShooter() instanceof Player)) {
            return;
        }

        if (event.getFinalDamage() == 0) {
            return;
        }

        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        DamageIndicator damageIndicator = new DamageIndicator(Math.round(event.getFinalDamage()) * -10, 60, player);
        damageIndicator.displayDamage(Util.randomLocation(event.getEntity().getLocation().add(0, 1.5, 0), 4));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHeal(EntityRegainHealthEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        DamageIndicator damageIndicator = new DamageIndicator(Math.round(event.getAmount()), 40, player);
        damageIndicator.displayDamage(Util.randomLocation(event.getEntity().getLocation().add(0, 1.5, 0), 4));
    }
}
