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

package net.adoptopenjdk.v3.vanilla.internal;

import net.adoptopenjdk.v3.api.AOV3Architecture;
import net.adoptopenjdk.v3.api.AOV3Exception;
import net.adoptopenjdk.v3.api.AOV3HeapSize;
import net.adoptopenjdk.v3.api.AOV3ImageKind;
import net.adoptopenjdk.v3.api.AOV3JVMImplementation;
import net.adoptopenjdk.v3.api.AOV3OperatingSystem;
import net.adoptopenjdk.v3.api.AOV3RequestBinaryForReleaseType;
import net.adoptopenjdk.v3.api.AOV3Vendor;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

final class AOV3RequestBinaryForRelease
  implements AOV3RequestBinaryForReleaseType
{
  private final AOV3Client client;
  private final AOV3Architecture architecture;
  private final AOV3HeapSize heapSize;
  private final AOV3ImageKind imageKind;
  private final AOV3JVMImplementation jvmImplementation;
  private final AOV3OperatingSystem operatingSystem;
  private final AOV3Vendor vendor;
  private final Optional<String> project;
  private final String releaseName;

  AOV3RequestBinaryForRelease(
    final AOV3Client inClient,
    final AOV3Architecture inArchitecture,
    final AOV3HeapSize inHeapSize,
    final AOV3ImageKind inImageKind,
    final AOV3JVMImplementation inJvmImplementation,
    final AOV3OperatingSystem inOperatingSystem,
    final AOV3Vendor inVendor,
    final Optional<String> inProject,
    final String inReleaseName)
  {
    this.client =
      Objects.requireNonNull(inClient, "inClient");
    this.architecture =
      Objects.requireNonNull(inArchitecture, "inArchitecture");
    this.heapSize =
      Objects.requireNonNull(inHeapSize, "inHeapSize");
    this.imageKind =
      Objects.requireNonNull(inImageKind, "inImageKind");
    this.jvmImplementation =
      Objects.requireNonNull(inJvmImplementation, "inJvmImplementation");
    this.operatingSystem =
      Objects.requireNonNull(inOperatingSystem, "inOperatingSystem");
    this.vendor =
      Objects.requireNonNull(inVendor, "inVendor");
    this.project =
      Objects.requireNonNull(inProject, "inProject");
    this.releaseName =
      Objects.requireNonNull(inReleaseName, "releaseName");
  }

  @Override
  public URI execute()
    throws AOV3Exception, InterruptedException
  {
    final var uriBuilder = new StringBuilder(128);
    uriBuilder.append(this.client.baseURI());
    uriBuilder.append("/binary/version/");
    uriBuilder.append(this.releaseName);
    uriBuilder.append("/");
    uriBuilder.append(this.operatingSystem.nameText());
    uriBuilder.append("/");
    uriBuilder.append(this.architecture.nameText());
    uriBuilder.append("/");
    uriBuilder.append(this.imageKind.nameText());
    uriBuilder.append("/");
    uriBuilder.append(this.jvmImplementation.nameText());
    uriBuilder.append("/");
    uriBuilder.append(this.heapSize.nameText());
    uriBuilder.append("/");
    uriBuilder.append(this.vendor.nameText());

    this.project.ifPresent(name -> {
      uriBuilder.append("?project=");
      uriBuilder.append(name);
    });

    return this.client.uriFor(uriBuilder.toString());
  }
}
