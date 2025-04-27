export default function SearchBar({ setSearchQuery }) {
  function handleChange(e) {
    setSearchQuery(e.target.value);
  }

  return (
    <div style={{ marginBottom: "20px", display: "flex", justifyContent: "center" }}>
      <input
        type="text"
        placeholder="Search by company or role..."
        onChange={handleChange}
        style={{
          width: "100%",
          maxWidth: "400px",
          padding: "8px",
          borderRadius: "5px",
          border: "1px solid #ccc"
        }}
      />
    </div>
  );
}
