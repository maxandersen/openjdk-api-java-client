/*
 * Copyright © 2020 Mark Raynsford <code@io7m.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.adoptopenjdk.v3.api;

import java.net.URI;

/**
 * Request binaries for the latest release.
 *
 * @see "https://api.adoptopenjdk.net/swagger-ui/#/Binary/get_v3_binary_latest_
 * _feature_version___release_type___os___arch___image_type___jvm_impl__
 * _heap_size___vendor_"
 */

public interface AOV3RequestBinaryForLatestType
  extends AOV3RequestType<URI>
{

}
