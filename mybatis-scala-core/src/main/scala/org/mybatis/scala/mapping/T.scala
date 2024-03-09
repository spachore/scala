/*
 *    Copyright 2011-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.scala.mapping

import scala.reflect.ClassTag

/** Utility to wrap types with reflection capabilities via Manifest.
  * Use T[MyType] instead of classOf[MyType]
 *
 * @tparam t wrapped type
  */
class T[t: ClassTag](implicit val ct: ClassTag[t]) {
  val raw: Class[Any] = ct.runtimeClass.asInstanceOf[Class[Any]]
  val unwrap: Class[t] = ct.runtimeClass.asInstanceOf[Class[t]]
  val isVoid = unwrap == java.lang.Void.TYPE
}

/** Syntactic sugar to support "T[MyType]" instead of new T[MyType] */
object T {
  def apply[t](implicit ct: ClassTag[t]) = new T[t]
}
