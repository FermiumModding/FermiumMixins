package fermiummixins.config;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

import java.util.HashSet;
import java.util.Set;

public class GalactiCraftConfig {
	
	@Config.Comment("Allows for making defined entities immune to GalactiCraft suffocation")
	@Config.Name("Enable Suffocation Immunity Handling")
	@Config.RequiresMcRestart
	public boolean suffocationImmunityHandling = false;
	
	@Config.Comment("List of entity registry names that should be immune to GalactiCraft suffocation" + "\n" +
			"Requires \"Enable Suffocation Immunity Handling\" enabled")
	@Config.Name("Suffocation Immune Entities")
	public String[] suffocationImmuneEntities = {""};
	
	private Set<ResourceLocation> suffocationImmuneLocs = null;
	
	public boolean isEntityImmune(EntityLivingBase entity) {
		if(this.suffocationImmuneLocs == null) {
			this.suffocationImmuneLocs = new HashSet<>();
			for(String name : this.suffocationImmuneEntities) {
				this.suffocationImmuneLocs.add(new ResourceLocation(name.trim().toLowerCase()));
			}
		}
		return this.suffocationImmuneLocs.contains(EntityList.getKey(entity));
	}
	
	public void refreshConfig() {
		this.suffocationImmuneLocs = null;
	}
}