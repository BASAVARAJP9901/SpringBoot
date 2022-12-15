/*
 * Copyright 2012-2019 the original author or authors.
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

package org.springframework.boot.actuate.autoconfigure.info;

import org.springframework.boot.actuate.info.InfoPropertiesInfoContributor.Mode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for core info contributors.
 *
 * @author Stephane Nicoll
 * @since 2.0.0
 */
@ConfigurationProperties("management.info")
public class InfoContributorProperties {

	private final Git git = new Git();

	public Git getGit() {
		return this.git;
	}

	public static class Git {

		/**
		 * Mode to use to expose git information.
		 */
		private Mode mode = Mode.SIMPLE;

		public Mode getMode() {
			return this.mode;
		}

		public void setMode(Mode mode) {
			this.mode = mode;
		}

	}

}
