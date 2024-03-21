package pollub.projekt.ddd.post.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CountByCategory implements CountPostCommand {
    private final PostRepository postRepository;
    private final String category;

    @Override
    public Integer execute() {
        return postRepository.countPostsByCategory(category);
    }
}
