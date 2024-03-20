package pollub.projekt.ddd.post.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CountAll implements CountPostCommand {
    private final PostRepository postRepository;
    @Override
    public Integer execute() {
        return postRepository.countPosts();
    }
}
