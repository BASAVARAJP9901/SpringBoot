/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.testcontainers.service.connection.otlp;

import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;

import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpTracingConnectionDetails;
import org.springframework.boot.testcontainers.service.connection.ContainerConnectionDetailsFactory;
import org.springframework.boot.testcontainers.service.connection.ContainerConnectionSource;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

/**
 * {@link ContainerConnectionDetailsFactory} to create
 * {@link OtlpTracingConnectionDetails} from a
 * {@link ServiceConnection @ServiceConnection}-annotated {@link GenericContainer} using
 * the {@code "otel/opentelemetry-collector-contrib"} image.
 *
 * @author Eddú Meléndez
 */
class OpenTelemetryTracingConnectionDetailsFactory
		extends ContainerConnectionDetailsFactory<OtlpTracingConnectionDetails, Container<?>> {

	OpenTelemetryTracingConnectionDetailsFactory() {
		super("otel/opentelemetry-collector-contrib",
				"org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpAutoConfiguration");
	}

	@Override
	protected OtlpTracingConnectionDetails getContainerConnectionDetails(
			ContainerConnectionSource<Container<?>> source) {
		return new OpenTelemetryTracingConnectionDetails(source);
	}

	private static final class OpenTelemetryTracingConnectionDetails extends ContainerConnectionDetails
			implements OtlpTracingConnectionDetails {

		private final String endpoint;

		private OpenTelemetryTracingConnectionDetails(ContainerConnectionSource<Container<?>> source) {
			super(source);
			this.endpoint = "http://" + source.getContainer().getHost() + ":"
					+ source.getContainer().getMappedPort(4318) + "/v1/traces";
		}

		@Override
		public String getEndpoint() {
			return this.endpoint;
		}

	}

}
