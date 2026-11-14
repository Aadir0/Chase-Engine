package renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.*;

import org.joml.*;
import org.lwjgl.BufferUtils;

public class Shader {

    private int shaderProgramID;
    private boolean beingUsed = false;
    
    private String vertexSource;
    private String fragmentSource;
    private String filePath;
    private int vertexID, fragmentID;

    public Shader(String filepath)
    {
        this.filePath = filepath;
        try {
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            // Find the first pattern after #type 'pattern'
            int index = source.indexOf("#type") + 6;
            int eol = source.indexOf(System.lineSeparator(), index);
            String firstPattern = source.substring(index, eol).trim();

            // Find the second pattern after #type 'pattern'
            index = source.indexOf("#type", eol) + 6;
            eol = source.indexOf(System.lineSeparator(), index);
            String secondPattern = source.substring(index, eol).trim();

            if (firstPattern.equals("vertex")) {
                vertexSource = splitString[1];
            } else if (firstPattern.equals("fragment")) {
                fragmentSource = splitString[1];
            } else {
                throw new IOException("Unexpected token '" + firstPattern + "'");
            }

            if (secondPattern.equals("vertex")) {
                vertexSource = splitString[2];
            } else if (secondPattern.equals("fragment")) {
                fragmentSource = splitString[2];
            } else {
                throw new IOException("Unexpected token '" + secondPattern + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Error: Could not open file for shader: '" + filepath + "'";
        }
    }

    public void compile()
    {
        // Compile and link the shaders

        //First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        
        //Pass the shader source to the GPU
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        // Check for errors in Compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '"+ filePath +"'\n\tVertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        //First load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        //Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        // Check for errors in Compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '\"+ filePath +\"'\n\tFragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }
    }

    public void link()
    {
        // Link shaders and check for errors
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        // Check for linking errors
        int success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '\"+ filePath +\"'\n\tLinking shaders failed");
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            assert false : "";
        }
    }

    public void use()
    {
        if (!beingUsed) {
        // Bind shader program
            glUseProgram(shaderProgramID);
            beingUsed = true;
        }
    }

    public void detach()
    {
        glUseProgram(0);
        beingUsed = false;
    }

    public void uploadMat4f(String varName, Matrix4f mat4)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLoaction, false, matBuffer);
    }

    public void uploadMat3f(String varName, Matrix3f mat3)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        mat3.get(matBuffer);
        glUniformMatrix3fv(varLoaction, false, matBuffer);
    }

    public void uploadVec4(String varName, Vector4f vec)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform4f(varLoaction, vec.x, vec.y, vec.z, vec.w);
    }

    public void uploadVec3(String varName, Vector3f vec)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform3f(varLoaction, vec.x, vec.y, vec.z);
    }

    public void uploadVec2(String varName, Vector2f vec)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform2f(varLoaction, vec.x, vec.y);
    }

    public void uploadFloat(String varName, float val)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1f(varLoaction, val);
    }

    public void uploadInt(String varName, int val)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLoaction, val);
    }

    public void uploadTexture(String varName, int slot)
    {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLoaction, slot);
    }

    public void uploadIntArray(String varName, int[] array) {
        int varLoaction = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1iv(varLoaction, array);
    }
}