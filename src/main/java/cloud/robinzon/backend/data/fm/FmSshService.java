/**
 * Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.data.fm;

import cloud.robinzon.backend.common.Properties;
import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class FmSshService {

    private final Properties prop;

    public FmSshService(Properties properties) {
        this.prop = properties;
    }

    private final JSch jsch = new JSch();

    private Session session(String fmIp) throws JSchException {
        System.out.println("[FmSshService][session]");
        int port = 22;
        Session session = this.jsch.getSession(prop.getSshFmAdminLogin(), fmIp, port);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(prop.getSshFmAdminPassword());
        return session;
    }

    @SuppressWarnings("unused")
    public String get(String fmIp) throws JSchException, IOException {
        System.out.println("[FmSshService][get]");
        Session session = session(fmIp);
        session.connect();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(prop.getSshFmAdminCommand());
        InputStream commandOutput = channel.getInputStream();
        channel.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(commandOutput));
        String output = reader.readLine();
        channel.disconnect();
        session.disconnect();
        System.out.println(output);
        return output;
    }

}
