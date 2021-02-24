package derp.core.dev.managers;

import derp.core.dev.utils.SkullMaker;

public enum SkullManager {

    ADMIN(new SkullMaker("The Admin's Suit","&cThis is an admin suit!","http://textures.minecraft.net/texture/16ae569be482483276916e78e985e6d12b88b1cb79954fb2b4ad9741d53a556")),

    ;

    private final SkullMaker skull;

    SkullManager(SkullMaker skullMaker) {
        this.skull = skullMaker;
    }

    public SkullMaker getSkull() {
        return skull;
    }
}
