/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.apache.camel.examples;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelConfiguration extends RouteBuilder {
  
  @Override
  public void configure() throws Exception {
    from("timer:kickstartmyroute?period=5000")
      .setBody(simple("Oh!..... yea!..... Kickstart my route! [${date:now}]"))
      .log("Sending message: [${body}]")
      .toF("kafka:TEST.FOO?securityProtocol=SSL&sslKeystoreLocation=%s&sslKeystorePassword=RAW(%s)", "/Users/jreagan/Development/Projects/Other_Projects/joshdreagan/camel-kafka-authz/alice-keystore.p12", "Abcd1234")
    ;
    
    fromF("kafka:TEST.FOO?groupId=TEST.FOO&securityProtocol=SSL&sslKeystoreLocation=%s&sslKeystorePassword=RAW(%s)", "/Users/jreagan/Development/Projects/Other_Projects/joshdreagan/camel-kafka-authz/bob-keystore.p12", "Abcd1234")
      .log("Got a message: [${body}]")
    ;
  }
}
