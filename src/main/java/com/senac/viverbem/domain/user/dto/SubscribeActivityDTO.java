package com.senac.viverbem.domain.user.dto;

import com.senac.viverbem.domain.user.dto.enums.Action;

public record SubscribeActivityDTO(Action action, Long activityId) {
}
