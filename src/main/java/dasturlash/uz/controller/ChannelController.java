package dasturlash.uz.controller;

import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.service.ChannelService;
import dasturlash.uz.util.SpringSecurityUtil;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/create")
    @Secured("USER")
    public ResponseEntity<ChannelDTO> createChannel(
            @RequestBody ChannelDTO dto
    ) {
        return ResponseEntity.ok(channelService.create(dto));
    }

    @PutMapping("/update-channel")
    @RolesAllowed({"USER","OWNER"})
    public ResponseEntity<ChannelDTO> updateChannel(
            @RequestParam String id,
            @RequestBody ChannelDTO dto
    ) {
        return ResponseEntity.ok(channelService.update(id, dto));
    }

    @PutMapping("/update-channel-photo")
    @RolesAllowed({"USER","OWNER"})
    public ResponseEntity<ChannelDTO> updateChannelPhoto(
            @RequestParam String id,
            @RequestParam ChannelDTO dto
    ) {
        return ResponseEntity.ok(channelService.updatePhoto(id, dto));
    }

    @PutMapping("/update-channel-banner")
    @RolesAllowed({"USER","OWNER"})
    public ResponseEntity<ChannelDTO> updateBanner(
            @RequestParam ChannelDTO dto
    ) {
        return ResponseEntity.ok(channelService.updateBanner(dto));
    }

    @GetMapping("/all-channels")
    @Secured("ADMIN")
    public ResponseEntity<Iterable<ChannelDTO>> getAllChannels() {
        return ResponseEntity.ok(channelService.getAllChannels());
    }

    @GetMapping("/channel")
    public ResponseEntity<ChannelDTO> getChannel(
            @RequestParam String id) {
        return ResponseEntity.ok(channelService.getDTO(id));
    }

    @PutMapping("/channel-stat")
    @RolesAllowed({"ADMIN","USER","OWNER"})
    public ResponseEntity<ChannelDTO> updateChannelStat(
            @RequestParam String id,
            @RequestParam GeneralStatus stat
    ){
        return ResponseEntity.ok(channelService.changeStat(id,stat));
    }

    @GetMapping("/user-channel")
    public ResponseEntity<Iterable<ChannelDTO>> getUserChannel(
            @RequestParam String userId
    ){
        if(userId.isBlank()) {
            userId = SpringSecurityUtil.getUserId();
        }
        return ResponseEntity.ok(channelService.getUserChannels(userId));
    }
}
