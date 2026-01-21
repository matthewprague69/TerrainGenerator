varying vec4 vShadowCoord;
varying vec2 vTexCoord;
varying vec4 vColor;

uniform sampler2D uDiffuse;
uniform sampler2D uShadowMap;
uniform int uUseTexture;

float computeShadow(vec4 shadowCoord) {
    vec3 proj = shadowCoord.xyz / shadowCoord.w;
    if (proj.x < 0.0 || proj.x > 1.0 || proj.y < 0.0 || proj.y > 1.0) {
        return 1.0;
    }
    float depth = texture2D(uShadowMap, proj.xy).r;
    float bias = 0.003;
    return (proj.z - bias) > depth ? 0.5 : 1.0;
}

void main() {
    vec4 baseColor = vColor;
    if (uUseTexture == 1) {
        baseColor *= texture2D(uDiffuse, vTexCoord);
    }
    float shadow = computeShadow(vShadowCoord * 0.5 + 0.5);
    gl_FragColor = vec4(baseColor.rgb * shadow, baseColor.a);
}
