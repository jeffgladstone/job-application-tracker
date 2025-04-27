export default function SearchBar({ setSearchQuery }) {
  function handleChange(e) {
    setSearchQuery(e.target.value);
  }

  return (
    <div style={{ marginBottom: "20px" }}>
      <input
        type="text"
        placeholder="Search by company or role..."
        onChange={handleChange}
        style={{ width: "100%", padding: "8px" }}
      />
    </div>
  );
}
