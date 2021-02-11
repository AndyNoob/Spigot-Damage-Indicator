package comfortable_andy.damageindicator;

import comfortable_andy.damageindicator.runnable.RemoveRunnable;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;

public class DamageIndicator {

    private final double damageDealt;
    private final long alive;
    private final Player player;

    public DamageIndicator(double damageDealt, long alive, Player player) {
        this.damageDealt = damageDealt;
        this.alive = alive;
        this.player = player;
    }

    public void displayDamage(Location location) {
        ArmorStand armorStand;

        try {
            armorStand = location.getWorld().spawn(location, ArmorStand.class, settings -> {
                settings.setVisible(false);
                settings.setGravity(false);
                settings.setBasePlate(false);
                settings.setArms(false);
                settings.setSmall(true);
                settings.setMarker(true);

                if (damageDealt < 0) {
                    settings.setCustomName(ChatColor.RED + "" + damageDealt / 20 + "❤");
                } else {
                    settings.setCustomName(ChatColor.GREEN + "+" + damageDealt / 20 + "❤");
                }

                settings.setCustomNameVisible(true);
                settings.setCollidable(false);
                settings.addScoreboardTag("di");
            });
        } catch (Exception e) {
            armorStand = location.getWorld().spawn(location, ArmorStand.class);

            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setBasePlate(false);
            armorStand.setArms(false);
            armorStand.setSmall(true);
            armorStand.setMarker(true);

            if (damageDealt < 0) {
                armorStand.setCustomName(ChatColor.RED + "" + damageDealt / 20 + "❤");
            } else {
                armorStand.setCustomName(ChatColor.GREEN + "+" + damageDealt / 20 + "❤");
            }

            armorStand.setCustomNameVisible(true);
            armorStand.setCollidable(false);
            armorStand.addScoreboardTag("di");
        }

        if (player != null) {
            try {
                Object destroyPacket = Util.getNMSClass("PacketPlayOutEntityDestroy").getConstructor(int[].class).newInstance(new int[]{armorStand.getEntityId()});
                Util.sendPacket(player, destroyPacket);
            } catch (Exception ignored) {
            }
        }

        new RemoveRunnable(armorStand).runTaskLater(Main.INSTANCE, alive);
    }
}
