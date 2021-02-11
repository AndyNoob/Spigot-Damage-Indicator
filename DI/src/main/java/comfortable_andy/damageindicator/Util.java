package comfortable_andy.damageindicator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Random;

public class Util {

    public static Location randomLocation(Location location, int distance) {
        Random random = new Random();
        int radius = random.nextInt(distance);

        int x = random.nextInt(Math.max(1, radius));
        double z = Math.sqrt(Math.pow(radius, 2) - Math.pow(x, 2));

        if (random.nextBoolean()) {
            x *= -1;
        }

        if (random.nextBoolean()) {
            z *= -1;
        }

        return location.add(x, 0, z);
    }

    public static double round(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.format(value);
        return value;
    }

    public static void sendPacket(Player player, Object packet) throws ReflectiveOperationException {
        Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
        Object playerConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
        playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
    }

    public static Class<?> getNMSClass(String className) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + className;
        return Class.forName(name);
    }
}
