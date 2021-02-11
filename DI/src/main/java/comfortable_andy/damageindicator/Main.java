package comfortable_andy.damageindicator;

import comfortable_andy.damageindicator.listener.ArmorStandListener;
import comfortable_andy.damageindicator.listener.DamagedListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new DamagedListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getWorlds().forEach(world -> {
            world.getEntitiesByClasses(ArmorStand.class).stream().filter(e -> e.getScoreboardTags().contains("di")).forEach(Entity::remove);
        });
    }
}
