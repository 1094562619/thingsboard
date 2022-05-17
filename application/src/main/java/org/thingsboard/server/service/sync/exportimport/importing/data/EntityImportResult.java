/**
 * Copyright © 2016-2022 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.service.sync.exportimport.importing.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.ExportableEntity;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.utils.JsonTbEntity;
import org.thingsboard.server.utils.ThrowingRunnable;

@Data
public class EntityImportResult<E extends ExportableEntity<? extends EntityId>> {

    @JsonTbEntity
    private E savedEntity;
    @JsonTbEntity
    private E oldEntity;
    private EntityType entityType;

    @JsonIgnore
    private ThrowingRunnable saveReferencesCallback = () -> {};
    @JsonIgnore
    private ThrowingRunnable sendEventsCallback = () -> {};

    public void addSaveReferencesCallback(ThrowingRunnable callback) {
        this.saveReferencesCallback = this.saveReferencesCallback.andThen(callback);
    }

    public void addSendEventsCallback(ThrowingRunnable callback) {
        this.sendEventsCallback = this.sendEventsCallback.andThen(callback);
    }

}
