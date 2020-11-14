package travel.service.folkways;

import travel.dao.folkways.FolkwaysImage;

import java.util.List;

public interface FolkwaysImageService {
    /**
     *
     * @param folkwaysId 民俗Id
     * @return
     */
    List<FolkwaysImage> findByFolkwaysId(String folkwaysId);

    /**
     *
     * @param folkwaysImageId 民俗图片Id
     * @return boolean
     */
    boolean delete(Integer folkwaysImageId);
}
