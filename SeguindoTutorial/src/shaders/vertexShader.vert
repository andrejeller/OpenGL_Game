#version 400 core

in vec3 position;
in vec2 textureCoords;


out vec3 colour;
out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;

void main(void){

    //gl_Position = vec4(position, 1.0); //Inicial
    //gl_Position = transformationMatrix * vec4(position, 1.0); //Com matrix de transformação
    gl_Position = projectionMatrix * transformationMatrix * vec4(position, 1.0); // Adicionado projeçao
    gl_Position = viewMatrix * projectionMatrix * transformationMatrix * vec4(position, 1.0); //Adicionado "camera"
    pass_textureCoords = textureCoords;
    colour = vec3(position.x + 0.5, 0.0, position.y + 0.5);


}