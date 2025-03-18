left_stack = gets.chomp.chars  # 초기 문자열을 왼쪽 스택에 저장
right_stack = []               # 오른쪽 스택은 비어 있음
M = gets.to_i                  # 명령어 개수

M.times do
  command = gets.chomp.split

  case command[0]
  when 'L'
    right_stack.push(left_stack.pop) unless left_stack.empty?
  when 'D'
    left_stack.push(right_stack.pop) unless right_stack.empty?
  when 'B'
    left_stack.pop unless left_stack.empty?
  when 'P'
    left_stack.push(command[1])  # 새 문자 추가
  end
end

# 최종 결과 출력 (left_stack + right_stack을 합쳐서 문자열 생성)
puts (left_stack + right_stack.reverse).join
