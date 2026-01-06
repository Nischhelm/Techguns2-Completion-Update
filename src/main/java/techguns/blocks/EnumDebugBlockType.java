package techguns.blocks;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

public enum EnumDebugBlockType implements IStringSerializable {
	AIRMARKER,
	ANTIAIRMARKER,
	INTERIORMARKER_NORTH,
	INTERIORMARKER_EAST,
	INTERIORMARKER_SOUTH,
	INTERIORMARKER_WEST;

	@Override
	public @NotNull String getName() {
		return name().toLowerCase();
	}
}
