package techguns.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techguns.TGBlocks;
import techguns.Techguns;

/**
 * May only be implemented by Blocks
 */
public interface IGenericBlock {

	default void init(Block b, String name, boolean addToList) {
		b.setRegistryName(new ResourceLocation(Techguns.MODID,name));
		b.setTranslationKey(Techguns.MODID+"."+name);
		b.setCreativeTab(Techguns.tabTechgun);
		
		if(addToList) {
			TGBlocks.BLOCKLIST.add(this);
		}
	}
	
	/**
	 * return the correct itemblock for initialization
	 */
	ItemBlock createItemBlock();
	
	void registerBlock(RegistryEvent.Register<Block> event);
	
	@SideOnly(Side.CLIENT)
	void registerItemBlockModels();
}
