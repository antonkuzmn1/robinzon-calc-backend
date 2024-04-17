package cloud.robinzon.backend.data.vm.resources.history

import org.springframework.data.jpa.repository.JpaRepository

interface VmHistoryRepository : JpaRepository<VmHistory, Long> {}