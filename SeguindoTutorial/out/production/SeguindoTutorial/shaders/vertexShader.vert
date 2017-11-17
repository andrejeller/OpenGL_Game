#version 400 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

//out vec3 colour;
out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;

void main(void){

    vec4 worldPosition = transformationMatrix * vec4(position, 1.0);

    //gl_Position = vec4(position, 1.0); //Inicial
    //gl_Position = transformationMatrix * vec4(position, 1.0); //Com matrix de transformação
    //gl_Position = projectionMatrix * transformationMatrix * vec4(position, 1.0); // Adicionado projeçao

    gl_Position = viewMatrix * projectionMatrix * worldPosition; //Adicionado "camera"
    pass_textureCoords = textureCoords;
    //colour = vec3(position.x + 0.5, 0.0, position.y + 0.5);

    surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;
    toLightVector = lightPosition - worldPosition.xyz;

}