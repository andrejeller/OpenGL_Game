#version 400 core

in vec3 colour;
in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour;

void main(void){

    //out_Color = vec4(colour, 1.0);
    out_Color = texture(textureSampler, pass_textureCoords);
}