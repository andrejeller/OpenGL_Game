package models;

import textures.ModelTexture;

public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.modelTexture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public ModelTexture getModelTexture() {
        return modelTexture;
    }

    public void setModelTexture(ModelTexture modelTexture) {
        this.modelTexture = modelTexture;
    }
}
