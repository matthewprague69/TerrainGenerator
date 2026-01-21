varying vec4 vShadowCoord;
varying vec2 vTexCoord;
varying vec4 vColor;

uniform mat4 uLightMatrix;

void main() {
    vec4 worldPos = gl_Vertex;
    vShadowCoord = uLightMatrix * worldPos;
    vTexCoord = gl_MultiTexCoord0.st;
    vColor = gl_Color;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
