import React, { Component } from 'react';

class Posts extends Component {
  // Step 5: Initialize the component with a list of posts in state
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false
    };
  }

  // Step 6: Create loadPosts() to fetch data from the API
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => {
        // Assign the fetched data to the component state
        this.setState({ posts: data });
      })
      .catch(error => {
        console.error("Error fetching posts:", error);
      });
  }

  // Step 7: Implement componentDidMount() to call loadPosts()
  componentDidMount() {
    this.loadPosts();
  }

  // Step 9: Implement componentDidCatch() to display alert messages
  componentDidCatch(error, info) {
    alert("An error occurred in the Posts component: " + error.toString());
    this.setState({ hasError: true });
  }

  // Step 8: Implement render() to display the title and post
  render() {
    // Fallback UI in case of an error
    if (this.state.hasError) {
      return <h2>Something went wrong while loading the blog posts.</h2>;
    }

    return (
      <div style={{ padding: '20px', fontFamily: 'Arial' }}>
        <h2>Latest Blog Posts</h2>
        <hr />
        {/* Loop through the posts and display them */}
        {this.state.posts.map(post => (
          <div key={post.id} style={{ marginBottom: '20px', borderBottom: '1px solid #ccc' }}>
            <h3 style={{ color: '#0056b3', textTransform: 'capitalize' }}>
              {post.title}
            </h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;