package org.peter.common.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("common")
public interface FileApi {

}
